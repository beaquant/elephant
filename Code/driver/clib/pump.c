/*****************************************************************************
*
*	Copyright(c) 2017-2020 Author(Zhihao). As an unpublished work
*	All rights reserved.
*
*	Module Description:
*		This is a template file for source code file
*		(Fill in a detailed description of the module's function here)
*	$URL: 		$
*	$Author: 	$
*	$Revision:  $
*	$Date:  	$
*-----------------------------------------------------------------------------
*   The information contained herein is confidential property of Zhihao
*   The use, copying, transfer or disclosure of such information is prohibited 
*   except by express written agreement with Zhihao.
*****************************************************************************/


/*****************************************************************************
*	Include Section
*	add all #include here
*****************************************************************************/
#include "pigpio.h"
#include "ringbuf.h"

/*****************************************************************************
* Define section
* add all #define here
*****************************************************************************/
#define PI_UART			"/dev/ttyMMA"
#define UART_BAUD		(38400)
// #define UART_BAUD		(9600)

#define UART_TX_SIZE		256
#define UART_RX_SIZE		256

// #define RINGBUF_SIZE 4096

#define MASTER_ADDRESS		(0X30)
#define BROADCAST_ADDRESS	(0X5F)
#define STX					(0X02)
#define ETX					(0X03)

/****************************************************************************
* ADT section
*	add definition of user defined Data Type that only be used in this file  here
***************************************************************************/
typedef struct  {
	uint8_t address;
	uint8_t status;
	uint8_t data[125];
	uint8_t len;
	uint8_t checksum;
	uint8_t csErr;
}uartRxBuf_t;

enum{
	PARSER_STATE_START,
	PARSER_STATE_ADDRESS,
	PARSER_STATE_STATUS,
	PARSER_STATE_DATA,
	PARSER_STATE_END,
	PARSER_STATE_CS	
};

/******************************************************************************
* Function prototype section
* add prototypes for all functions called by this file,excepting those
* declared in header file
*****************************************************************************/


/*****************************************************************************
* Global variables section - Exported
* add declaration of global variables that will be exported here
* e.g.
*	int8_t foo;
****************************************************************************/
int8_t uartFd= -1;
// ringbuf_t uartRxBuf, uartTxBuf;
uartRxBuf_t uartRxBuf;

pthread_t       uartRxTid;


uint8_t parserState;


/*****************************************************************************
* Global variables section - Local
* define global variables(will be refered only in this file) here,
* static keyword should be used to limit scope of local variable to this file
* e.g.
*	static uint8_t ufoo;
*****************************************************************************/

/* function body */

/*****************************************************************************
* Description:
*		add funtion description here
* Parameters:
*		description for each argument, new argument starts at new line
* Return:
*		what does this function returned?
*****************************************************************************/
void pumpInit(void)
{
    int err;

	uartFd = serOpen(PI_UART, UART_BAUD, 0);
	if(uartFd < 0){
		return ;	
	}
	
	// uartTxBuf = ringbuf_new(UART_TX_SIZE -1);
	// uartRxBuf = ringbuf_new(UART_RX_SIZE -1);
	// ringbuf_reset(uartTxBuf);
	// ringbuf_reset(uartRxBuf);
	
	err = pthread_create(&uartRxTid, NULL, OEMRspHandler, NULL);
    if ( 0 != err ){
        printf("can't create thread for oem response handler:%s\n", strerror(err));
    }
    printf("%ld running\n", uartRxTid);

}

void OEMWrite(uint8_t address, uint8_t * data, uint8_t len)
{
	uint8_t i, checksum = 0;
	uint8_t buf[200];
	uint8_t ret = -1;
	
	buf[0] = STX;
	checksum = STX;
	buf[1] = address;
	checksum ^= address;
	for(i = 0; i < len; i++){
		buf[2 + i] = data[i];
		checksum ^= data[i];
	}
	buf[2 + i] = ETX;
	checksum ^= ETX;
	buf[2 + i + 1] = checksum;
	ret = serWrite(uartFd, buf, len + 4);
}

void OEMRspHandler(void *arg)
{
	// int8_t numbers = -1;
	uint8_t dat;
    printf("OEMRspHandler thread begin  %ld  \n", uartRxTid);
    pthread_setcancelstate(PTHREAD_CANCEL_ENABLE, NULL);           //允许退出线程
    pthread_setcanceltype(PTHREAD_CANCEL_ASYNCHRONOUS,   NULL);   //设置立即取消
	while(1){
		if(serDataAvailable(uartFd)){
			dat = serReadByte(uartFd);
			if(parserState != PARSER_STATE_START){
				uartRxBuf.checksum ^= dat;
			}
			
			switch (parserState){
				case PARSER_STATE_START:
					if(dat == STX){
						parserState = PARSER_STATE_ADDRESS;
						uartRxBuf.len = 0;
						uartRxBuf.checksum = STX;
						uartRxBuf.csErr = 0;
					}
				break;
				case PARSER_STATE_ADDRESS:
					uartRxBuf.address = dat;
					parserState = PARSER_STATE_STATUS;
				break;
				case PARSER_STATE_STATUS:
					uartRxBuf.status = dat;
					parserState = PARSER_STATE_DATA;
				break;
				case PARSER_STATE_DATA:
					if(dat != ETX){
						uartRxBuf.data[uartRxBuf.len] = dat;
						uartRxBuf.len++;
					}
					else{
						parserState = PARSER_STATE_CS;
					}
				break;
				case PARSER_STATE_CS:
					if(uartRxBuf.checksum != dat){
						uartRxBuf.csErr = 1;//error
					}
					else{
						uartRxBuf.csErr = 0;//ok
					}
					parserState = PARSER_STATE_START;
				break;
				default:break;
			}
		}
	}
}

/********************************End Of File********************************/

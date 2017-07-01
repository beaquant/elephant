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
#include <unistd.h>
#include "piconfig.h"
#include "types.h"

#include "74hc595.h"
#include "gpio.h"

/*****************************************************************************
* Define section
* add all #define here
*****************************************************************************/
#define   HC_DATA_H()        gpioWrite(GPIO_OUTPUT_595_DS,       HIGH)   // data line output high
#define   HC_DATA_L()        gpioWrite(GPIO_OUTPUT_595_DS,       LOW)   //date line output low
#define   HC_LANCH_H()       gpioWrite(GPIO_OUTPUT_595_STCP, HIGH)   // rck output high
#define   HC_LANCH_L()       gpioWrite(GPIO_OUTPUT_595_STCP, LOW)  // rck output low
#define   HC_CLOCK_H()       gpioWrite(GPIO_OUTPUT_595_SHCP, HIGH)   // sck output high
#define   HC_CLOCK_L()       gpioWrite(GPIO_OUTPUT_595_SHCP, LOW)  // sck output low


/****************************************************************************
* ADT section
*	add definition of user defined Data Type that only be used in this file  here
***************************************************************************/


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
uint16_t u16ExIOBuf;


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
void hc595Init(void)
{
	uint16_t buf = 0;
	buf |= 1 << IO_EX_595_BIT2_ADS7805_R_C;//make cs7805 stay low
	buf |= 1 << IO_EX_595_BIT4_TLC5615_CS;
//	buf |= 1 << IO_EX_595_BIT3_ADS7805_CS;//high


	u16ExIOBuf = buf;
	update595Output();
}
void setAll595Buf(void)
{
	u16ExIOBuf = 0xffff;
}
void set595Buf(uint16_t data)
{
	u16ExIOBuf = data;
}
uint16_t get595Buf(void)
{
	return u16ExIOBuf;
}
void reset595Buf(void)
{
    u16ExIOBuf = 0;
}
void set595BufByBit(uint8_t index)
{
    u16ExIOBuf |= 0x0001 << index;
}
void clr595BufByBit(uint8_t index)
{
    u16ExIOBuf &= ~(0x0001 << index);
}
void ctrl595BufByBit(uint8_t index, uint8_t ctrl)
{
    if(ctrl == 0){
    	clr595BufByBit(index);
    }
    else{
    	set595BufByBit(index);
    }
}
void update595Output(void)
{
    uint8_t i;
    uint16_t temp;
    temp = u16ExIOBuf;
    HC_LANCH_L(); //latch open
    usleep(1000);
    HC_CLOCK_L();
    usleep(1000);
    for(i = 0; i < IO_EX_595_DATA_LEN; i++){
        HC_CLOCK_L();
	usleep(1000);
        if(temp & 0x8000){
            HC_DATA_H();
        }
        else{
            HC_DATA_L();
        }
	usleep(1000);
        HC_CLOCK_H();
        usleep(1000);
        temp <<= 1;
    }
    usleep(1000);
    HC_LANCH_H();  
    usleep(1000);
    HC_LANCH_L();
}


/********************************End Of File********************************/

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
void setAll595Buf()
{
	u16ExIOBuf = 0xffff;
}
void set595Buf(uint16_t data)
{
	u16ExIOBuf = data;
}
uint16_t get595Buf()
{
	return u16ExIOBuf;
}
void reset595Buf()
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
void update595Output(void)
{
    uint8_t i;
    uint16_t temp;
    temp = u16ExIOBuf;
    HC_LANCH_L(); //latch open
    HC_CLOCK_L();
    for(i = 0; i < IO_EX_595_DATA_LEN; i++){
        if(temp & 0x0001){
            HC_DATA_H();
        }
        else{
            HC_DATA_L();
        }
        HC_CLOCK_H();
        usleep(10);
        HC_CLOCK_L();
        temp >>= 1;
    }
    HC_LANCH_H();
    usleep(10);
    HC_LANCH_L();
}


/********************************End Of File********************************/

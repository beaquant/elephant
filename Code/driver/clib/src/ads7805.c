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
#include "types.h"
#include "piconfig.h"
#include "74hc595.h"
#include "ads7805.h"
#include "gpio.h"



/*****************************************************************************
* Define section
* add all #define here
*****************************************************************************/

/****************************************************************************
* ADT section
*	add definition of user defined Data Type that only be used in this file  here
***************************************************************************/
enum{
    ADS7805STATE_IDLE = 0,
    ADS7805STATE_START_CONV,
    ADS7805STATE_CHECK_BUSY,
    ADS7805STATE_OUTPUTDATA,
    ADS7805STATE_GETDATA
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
uint8_t ads7805State = 0;
uint16_t ads7805DATA = 0;

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
void ads7805StartConversion(void)//
{
    clr595BufByBit(IO_EX_595_BIT3_ADS7805_CS);//cs stay low
    set595BufByBit(IO_EX_595_BIT2_ADS7805_R_C);//rc stay high
    update595Output();
    
    clr595BufByBit(IO_EX_595_BIT2_ADS7805_R_C);//rc falling edge
    update595Output();
    // usleep(1);
    set595BufByBit(IO_EX_595_BIT2_ADS7805_R_C);
    update595Output();
}
/* when busy is high */
void ads7805StartOutputData(void)
{
    set595BufByBit(IO_EX_595_BIT2_ADS7805_R_C);//rc stay high
    set595BufByBit(IO_EX_595_BIT3_ADS7805_CS);//cs stay high
    update595Output();
    
    clr595BufByBit(IO_EX_595_BIT2_ADS7805_R_C);//cs falling edge
    update595Output();
    // usleep(1);
    set595BufByBit(IO_EX_595_BIT2_ADS7805_R_C);
    update595Output();
}
uint8_t ads7805ConversionStatus(void)
{
    return gpioRead(GPIO_INPUT_ADS7805_BUSY);//0:busy, 1:ok
}

uint16_t ads7805GetData(void)
{
    uint8_t idx;
    uint16_t temp = 0;
    clr595BufByBit(IO_EX_595_BIT1_ADS7805_BYTE);//BYTE stay low
    update595Output();
    for(idx = GPIO_INDEX_ADS7805_A7; idx >= GPIO_INDEX_ADS7805_A0; idx--){
        temp |= gpioRead(raspiGpio[idx].gpio);
        temp <<= 1;
    }
    
    set595BufByBit(IO_EX_595_BIT1_ADS7805_BYTE);//BYTE stay high
    update595Output();
    for(idx = GPIO_INDEX_ADS7805_A7; idx >= GPIO_INDEX_ADS7805_A0; idx--){
        temp |= gpioRead(raspiGpio[idx].gpio);
        temp <<= 1;
    }
    return temp;
}

void ads7805StateUpdate(void)
{
    switch(ads7805State){
    case ADS7805STATE_IDLE  :
    
    break;
    
    case ADS7805STATE_START_CONV:
        ads7805StartConversion();
        ads7805State = ADS7805STATE_CHECK_BUSY;
    break;
    case ADS7805STATE_CHECK_BUSY:
        if(ads7805ConversionStatus()){
            ads7805State = ADS7805STATE_OUTPUTDATA;
        }
    break;
    case ADS7805STATE_OUTPUTDATA:
        ads7805StartOutputData();
        ads7805State = ADS7805STATE_GETDATA;
    break;
    case ADS7805STATE_GETDATA:
    	ads7805DATA = ads7805GetData();
        ads7805State = ADS7805STATE_IDLE;
    break;
    default:break;
    }
}
uint8_t ads7805Start(void)
{
	if(ads7805State != ADS7805STATE_IDLE){
		return ADS7805_NOK_BUSY;
	}else{
		ads7805State = ADS7805STATE_START_CONV;
		return ADS7805_OK;
	}
}
uint8_t ads7805Result(uint16_t * data)
{
	if(ads7805State != ADS7805STATE_IDLE){
		return ADS7805_NOK_BUSY;
	}else{
		* data = ads7805DATA;
		return ADS7805_OK;
	}
}
/********************************End Of File********************************/

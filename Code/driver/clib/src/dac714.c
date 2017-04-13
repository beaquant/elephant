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
#include "gpio.h"
#include "dac714.h"
#include "piconfig.h"


/*****************************************************************************
* Define section
* add all #define here
*****************************************************************************/

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

/*****************************************************************************
* Global variables section - Local
* define global variables(will be refered only in this file) here,
* static keyword should be used to limit scope of local variable to this file
* e.g.
*	static uint8_t ufoo;
*****************************************************************************/

/* function body */
void dac714Init(void)
{
	
}
void dac714Set(uint16_t data)
{
	uint8_t byte0, byte1;
    gpioWrite(GPIO_OUTPUT_DAC714_AA0,    LOW);
	byte0 = (data >> 8);
	byte1 = data & 0xFF;
//	SPI.transfer(byte0);
//	SPI.transfer(byte1);
	gpioWrite(GPIO_OUTPUT_DAC714_AA0, HIGH);

	gpioWrite(GPIO_OUTPUT_DAC714_AA1, LOW);
	gpioWrite(GPIO_OUTPUT_DAC714_AA1, HIGH);
}

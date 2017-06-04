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
#include "74hc595.h"
#include "spi.h"



/*****************************************************************************
* Define section
* add all #define here
*****************************************************************************/
#define V_REF_2500_MV					2500
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

/*****************************************************************************
* Description:
*		add funtion description here
* Parameters:
*		description for each argument, new argument starts at new line
* Return:
*		what does this function returned?
*****************************************************************************/
void tlc5615Init(void)
{
	
}

void tlc5615Set(uint16_t a_mv, uint16_t b_mv)
{
//	uint8_t byte0, byte1;
//	clrIoRefresh(IO_EX_595_BIT4_TLC5615_CS);
//	byte0 = (data_b >> 8);
//	byte1 = data_b & 0xFF;
////	SPI.transfer(byte0);
////	SPI.transfer(byte1);
//	byte0 = (data_a >> 8);
//	byte1 = data_a & 0xFF;
////	SPI.transfer(byte0);
////	SPI.transfer(byte1);
//
//	setIoRefresh(IO_EX_595_BIT4_TLC5615_CS);
	uint16_t data_a,  data_b;
	uint8_t buf[4];

	data_a = (a_mv*1024)/(2*V_REF_2500_MV);
	data_b = (b_mv*1024)/(2*V_REF_2500_MV);

	buf[0] = (data_b >> 8);
	buf[1] = data_b & 0xFF;
	buf[2] = (data_a >> 8);
	buf[3] = data_a & 0xFF;
	raspiSpiWrite(SPI_INDEX_5615, (char *)buf, 4);

}
/********************************End Of File********************************/

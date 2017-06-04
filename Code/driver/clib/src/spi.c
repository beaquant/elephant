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
#include "gpio.h"
#include "74hc595.h"
#include "spi.h"
#include <stdio.h>

/*****************************************************************************
* Define section
* add all #define here
*****************************************************************************/
#define SPI_CS_7715(ctrl)		gpioWrite(GPIO_OUTPUT_SPI_CS_7715,       ctrl)

#define SPI_CS_5541(ctrl)		gpioWrite(GPIO_OUTPUT_SPI_CS_5541,       ctrl)
#define SPI_CS_7805(ctrl)		ctrlIoRefresh(IO_EX_595_BIT3_ADS7805_CS,  ctrl)
#define SPI_CS_5615(ctrl)		ctrlIoRefresh(IO_EX_595_BIT4_TLC5615_CS,   ctrl)

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
#define SPEED 1000000
#define SPI_5615_SPEED		30000
int handler = -1;
/* function body */

/*****************************************************************************
* Description:
*		add funtion description here
* Parameters:
*		description for each argument, new argument starts at new line
* Return:
*		what does this function returned?
*****************************************************************************/
void raspiSpiDeInit(void)
{
	if(handler >= 0){
		spiClose(handler);
	}
}
void raspiSpiInit(unsigned spiChan, unsigned baud)
{
//	handler = spiOpen(spiChan, baud, 0x00e0);
	handler = spiOpen(0, SPEED, 0x00e0);
	if(handler >= 0){
		printf("spi init success\n");
	}
}
void raspiSpiCsCtrl(unsigned index, unsigned ctrl)
{
	switch(index){
	case SPI_INDEX_7715:
		SPI_CS_7715(ctrl);
		break;
	case SPI_INDEX_5541:
		SPI_CS_5541(ctrl);
		break;
	case SPI_INDEX_7805:
		SPI_CS_7805(ctrl);
		break;
	case SPI_INDEX_5615:
		SPI_CS_5615(ctrl);
		break;
	default:break;
	}
}
void raspiSpiWrite(unsigned index, char * buf, unsigned count)
{
	raspiSpiCsCtrl(index, LOW);
	spiWrite(handler, buf, count);
	raspiSpiCsCtrl(index, HIGH);
}



/********************************End Of File********************************/

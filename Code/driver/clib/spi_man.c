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
#include "ringbuf.h"
#include "spi_man.h"



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
SPI_CTRL_T spi_buf[SPI_BUF_MAX];
struct ringbuf spi_ringbuf;
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
void spi_man_init(void)
{
	ringbuf(&spi_ringbuf, spi_buf, SPI_BUF_MAX);
	// spiOpen(
	
}

uint8_t spi_man_write(uint8_t index, uint8_t *data, uint8_t len)
{
	SPI_CTRL_T buf;
	uint8_t i;
	// if(ringbuf_avaliable(&spi_ringbuf) <= 0{
		// return 1;
	// }
	if(len >= SPI_DATA_MAX){
		return 0;
	}
	
	buf.index = index;
	buf.len = len;
	for(i = 0; i < len; i++){
		buf.data[i] = data[i];
	}
	
	return ringbuf_put(&spi_ringbuf, buf);
	
}

uint8_t spi_man_trasmit(void)
{
	SPI_CTRL_T buf;
	if(ringbuf_get(&spi_ringbuf, buf) < 0){
		return 0;
	}
	buf.index;
}
/********************************End Of File********************************/

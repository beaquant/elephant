/*****************************************************************************
*
*	Copyright(c) 2017-2020 Author(Zhihao). As an unpublished work
*	All rights reserved.
*
*	Description:
*		This is a template file for header file
*		(Fill in a detailed description of the module's function here)
*
*	$URL: 		$
*	$Author: 	$
*	$Revision:  $
*	$Date:  	$
*
*-----------------------------------------------------------------------------
*   The information contained herein is confidential property of Zhihao
*   The use, copying, transfer or disclosure of such information is prohibited 
*   except by express written agreement with Zhihao.
*****************************************************************************/

#ifndef _SPI_H_
#define _SPI_H_

#ifdef __cplusplus
extern "C" {
#endif

/****************************************************************************
* #include section
*	add #include here if any
***************************************************************************/

/****************************************************************************
* #define section
*	add constant #define here if any
***************************************************************************/


/****************************************************************************
* ADT section
*	add Abstract Data Type definition here
***************************************************************************/
enum{
	SPI_INDEX_7715 = 0,
	SPI_INDEX_5541,
	SPI_INDEX_7805,
	SPI_INDEX_5615,
	SPI_INDEX_TOTAL
};


/****************************************************************************
*  extern variable declaration section
***************************************************************************/


/****************************************************************************
*  section
*	add function prototype here if any
***************************************************************************/
void raspiSpiDeInit(void);
void raspiSpiInit(unsigned spiChan, unsigned baud);
//void raspiSpiCsCtrl(unsigned index, unsigned ctrl);
void raspiSpiWrite(unsigned index, char * buf, unsigned count);




#ifdef __cplusplus
}
#endif

#endif


/********************************End Of File********************************/





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

#ifndef _SPI_MAN_H_
#define _SPI_MAN_H_

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
	SPI_INDEX_AD7715,
	SPI_INDEX_MAX5541,
	SPI_INDEX_DAC714,
	SPI_INDEX_TLC5615,
	SPI_MAX_NUMBER
};

#define SPI_DATA_MAX		30
typedef struct{
	uint8_t index;
	uint8_t data[SPI_DATA_MAX];
	uint8_t len;	
}SPI_CTRL_T;

// typedef struct{
	// SPI_CTRL_T buf;
	
	
// }
#define SPI_BUF_MAX			16

/****************************************************************************
*  extern variable declaration section
***************************************************************************/


/****************************************************************************
*  section
*	add function prototype here if any
***************************************************************************/




#ifdef __cplusplus
}
#endif

#endif


/********************************End Of File********************************/





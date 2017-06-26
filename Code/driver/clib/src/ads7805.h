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

#ifndef _ADS7805_H_
#define _ADS7805_H_

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
enum{
	ADS7805_OK = 0,
	ADS7805_NOK_BUSY
};

/****************************************************************************
* ADT section
*	add Abstract Data Type definition here
***************************************************************************/


/****************************************************************************
*  extern variable declaration section
***************************************************************************/


/****************************************************************************
*  section
*	add function prototype here if any
***************************************************************************/
void ads7805StartConversion(void);//
void ads7805StartOutputData(void);
uint8_t ads7805ConversionStatus(void);
uint16_t ads7805GetData(void);


uint8_t ads7805Start(void);
uint8_t ads7805Result(uint16_t * data);
void ads7805Init(void);


#ifdef __cplusplus
}
#endif

#endif


/********************************End Of File********************************/





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

#ifndef _PICONFIG_H_
#define _PICONFIG_H_

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
#define PI_ENVALUATE_GPIO_LIB
#undef PI_GPIO_LIB

#ifdef PI_GPIO_LIB
	#include <pigpio.h>
#endif
#ifdef PI_ENVALUATE_GPIO_LIB
	#include "pienv.h"
	
	
	
#define gpioSetMode				piEnv_gpioSetMode
#define gpioGlitchFilter		piEnv_gpioGlitchFilter
#define gpioWrite				piEnv_gpioWrite
#define gpioInitialise			piEnv_gpioInitialise
#define gpioTerminate			piEnv_gpioTerminate
#define gpioVersion				piEnv_gpioVersion
#define gpioHardwareRevision	piEnv_gpioHardwareRevision
#define gpioRead				piEnv_gpioRead
#define serOpen					piEnv_serOpen
#define serClose				piEnv_serClose
#define serWriteByte			piEnv_serWriteByte
#define serReadByte				piEnv_serReadByte
#define serWrite				piEnv_serWrite
#define serRead					piEnv_serRead
#define serDataAvailable		piEnv_serDataAvailable

#define spiOpen		piEnv_spiOpen
#define spiClose		piEnv_spiClose
#define spiRead		piEnv_spiRead
#define spiWrite		piEnv_spiWrite



#endif

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



#ifdef __cplusplus
}
#endif

#endif


/********************************End Of File********************************/





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

#ifndef _PIENV_H_
#define _PIENV_H_

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


/****************************************************************************
*  extern variable declaration section
***************************************************************************/


/****************************************************************************
*  section
*	add function prototype here if any
***************************************************************************/


int piEnv_gpioSetMode(unsigned gpio, unsigned mode);
int piEnv_gpioGlitchFilter(unsigned user_gpio, unsigned steady);
int piEnv_gpioWrite(unsigned gpio, unsigned level);
int piEnv_gpioInitialise(void);
void piEnv_gpioTerminate(void);
unsigned piEnv_gpioVersion(void);
unsigned piEnv_gpioHardwareRevision(void);
int piEnv_gpioRead(unsigned gpio);
int piEnv_serOpen(char *sertty, unsigned baud, unsigned serFlags);
int piEnv_serClose(unsigned handle);
int piEnv_serWriteByte(unsigned handle, unsigned bVal);
int piEnv_serReadByte(unsigned handle);
int piEnv_serWrite(unsigned handle, char *buf, unsigned count);
int piEnv_serRead(unsigned handle, char *buf, unsigned count);
int piEnv_serDataAvailable(unsigned handle);

int piEnv_spiOpen(unsigned spiChan, unsigned baud, unsigned spiFlags);
int piEnv_spiClose(unsigned handle);
int piEnv_spiRead(unsigned handle, char *buf, unsigned count);
int piEnv_spiWrite(unsigned handle, char *buf, unsigned count);

#ifdef __cplusplus
}
#endif

#endif


/********************************End Of File********************************/





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

/*****************************************************************************
* Description:
*		add funtion description here
* Parameters:
*		description for each argument, new argument starts at new line
* Return:
*		what does this function returned?
*****************************************************************************/
int piEnv_gpioSetMode(unsigned gpio, unsigned mode)
{
    return 0;
}

int piEnv_gpioGlitchFilter(unsigned user_gpio, unsigned steady)
{
    return 0;
}

int piEnv_gpioWrite(unsigned gpio, unsigned level)
{
    return 0;
}

int piEnv_gpioInitialise(void)
{
	return 0;
}

void piEnv_gpioTerminate(void)
{
	
}

unsigned piEnv_gpioVersion(void)
{
	return 0;
}
unsigned piEnv_gpioHardwareRevision(void)
{
	return 0;
}

int piEnv_gpioRead(unsigned gpio)
{
	return 0;
}

int piEnv_serOpen(char *sertty, unsigned baud, unsigned serFlags)
{
	return 0;
}

int piEnv_serClose(unsigned handle)
{
	return 0;
}
int piEnv_serWriteByte(unsigned handle, unsigned bVal)
{
	return 0;
}
int piEnv_serReadByte(unsigned handle)
{
	return 0;
}
int piEnv_serWrite(unsigned handle, char *buf, unsigned count)
{
	return 0;
}
int piEnv_serRead(unsigned handle, char *buf, unsigned count)
{
	return 0;
}
int piEnv_serDataAvailable(unsigned handle)
{
	return 0;
}

int piEnv_spiOpen(unsigned spiChan, unsigned baud, unsigned spiFlags)
{
	return 0;
}

int piEnv_spiClose(unsigned handle)
{
	return 0;
}
int piEnv_spiRead(unsigned handle, char *buf, unsigned count)
{
	return 0;
}
int piEnv_spiWrite(unsigned handle, char *buf, unsigned count)
{
	return 0;
}



/********************************End Of File********************************/

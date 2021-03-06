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
#include <stdio.h>
#include "gpio.h"
#include "piconfig.h"


/*****************************************************************************
* Define section
* add all #define here
*****************************************************************************/
#define     GPIO_INPUT_STEADY_FILTER        1//ms

/****************************************************************************
* ADT section
*	add definition of user defined Data Type that only be used in this file  here
***************************************************************************/
typedef struct{
    unsigned int gpio;
    unsigned int gpiotrg;
    unsigned int gpiopresstype;
}gpio_read_t;


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


const gpio_t raspiGpio[RASPI_GPIO_SIZE] = {
/* output */
{GPIO_OUTPUT_DAC714_AA0,        OUTPUT,         HIGH},
{GPIO_OUTPUT_DAC714_AA1,        OUTPUT,         HIGH},
{GPIO_OUTPUT_595_DS,            OUTPUT,         LOW},
{GPIO_OUTPUT_595_STCP,          OUTPUT,         LOW},
{GPIO_OUTPUT_595_SHCP,          OUTPUT,         LOW},
//{GPIO_OUTPUT_SPI_SIN,           OUTPUT,         LOW},
//{GPIO_OUTPUT_SPI_SCLK,          OUTPUT,         LOW},
{GPIO_OUTPUT_SPI_CS_7715,           OUTPUT,         HIGH},
{GPIO_OUTPUT_SPI_CS_5541,          OUTPUT,         HIGH},

/* input */
{GPIO_INPUT_AD7715_DOUT,        INPUT,          LOW},
{GPIO_INPUT_ADS7805_A0,         INPUT,          LOW},
{GPIO_INPUT_ADS7805_A1,         INPUT,          LOW},
{GPIO_INPUT_ADS7805_A2,         INPUT,          LOW},
{GPIO_INPUT_ADS7805_A3,         INPUT,          LOW},
{GPIO_INPUT_ADS7805_A4,         INPUT,          LOW},
{GPIO_INPUT_ADS7805_A5,         INPUT,          LOW},
{GPIO_INPUT_ADS7805_A6,         INPUT,          LOW},
{GPIO_INPUT_ADS7805_A7,         INPUT,          LOW},
{GPIO_INPUT_ADS7805_BUSY,       INPUT,          LOW}
};
/*****************************************************************************
* Global variables section - Local
* define global variables(will be refered only in this file) here,
* static keyword should be used to limit scope of local variable to this file
* e.g.
*	static uint8_t ufoo;
*****************************************************************************/

/* function body */

/****************************************************************/
int raspiGpioHwVer(void)
{
    return gpioHardwareRevision();
}
/****************************************************************/
int raspiGpioVer(void)
{
    return gpioVersion();
}
void raspiGpioSetMode(int gpio, int mode)
{
    if(mode == INPUT){
    	gpioSetMode(gpio,         INPUT);
        gpioGlitchFilter(gpio,         GPIO_INPUT_STEADY_FILTER);
    }
    else if(mode == OUTPUT){
    	gpioSetMode(gpio,         OUTPUT);
    }
}
uint8_t getGpioMode(uint16_t index)
{
	return gpioGetMode(index);
}
uint8_t getInput(uint16_t index)
{
	return gpioRead(index);
}
void setOutput(uint16_t index)
{
	gpioWrite(index,    1);
}
void clrOutput(uint16_t index)
{
	gpioWrite(index,    0);
}
void raspiGpioOutputCtrl(int gpio, int ctrl)
{
	gpioWrite(gpio,         ctrl);
}


/****************************************************************/
#if 0
void raspiGpioInit(void)
{
	int ret = gpioInitialise();
    if (ret<0) {
    	printf("gpio initialise fail\n");
    	return ;
    }
    printf("gpio initialise success:%d\n",ret);
    printf("gpioHardwareRevision:%d\n",raspiGpioHwVer());
    printf("raspiGpioVer:%d\n",raspiGpioVer());

    /* set gpio input mode */
    gpioSetMode(GPIO_INPUT_AD7715_DOUT,         INPUT);
    gpioSetMode(GPIO_INPUT_ADS7805_A0,          INPUT);
    gpioSetMode(GPIO_INPUT_ADS7805_A1,           INPUT);
    gpioSetMode(GPIO_INPUT_ADS7805_A2,           INPUT);
    gpioSetMode(GPIO_INPUT_ADS7805_A3,           INPUT);
    gpioSetMode(GPIO_INPUT_ADS7805_A4,           INPUT);
    gpioSetMode(GPIO_INPUT_ADS7805_A5,           INPUT);
    gpioSetMode(GPIO_INPUT_ADS7805_A6,           INPUT);
    gpioSetMode(GPIO_INPUT_ADS7805_A7,           INPUT);

    /* set gpio input filter */
    gpioGlitchFilter(GPIO_INPUT_AD7715_DOUT,         GPIO_INPUT_STEADY_FILTER);
    gpioGlitchFilter(GPIO_INPUT_ADS7805_A0,          GPIO_INPUT_STEADY_FILTER);
    gpioGlitchFilter(GPIO_INPUT_ADS7805_A1,           GPIO_INPUT_STEADY_FILTER);
    gpioGlitchFilter(GPIO_INPUT_ADS7805_A2,           GPIO_INPUT_STEADY_FILTER);
    gpioGlitchFilter(GPIO_INPUT_ADS7805_A3,           GPIO_INPUT_STEADY_FILTER);
    gpioGlitchFilter(GPIO_INPUT_ADS7805_A4,           GPIO_INPUT_STEADY_FILTER);
    gpioGlitchFilter(GPIO_INPUT_ADS7805_A5,           GPIO_INPUT_STEADY_FILTER);
    gpioGlitchFilter(GPIO_INPUT_ADS7805_A6,           GPIO_INPUT_STEADY_FILTER);
    gpioGlitchFilter(GPIO_INPUT_ADS7805_A7,           GPIO_INPUT_STEADY_FILTER);

    /* set gpio output mode */
    gpioSetMode(GPIO_OUTPUT_DAC714_AA0,    OUTPUT);
    gpioSetMode(GPIO_OUTPUT_DAC714_AA1,       OUTPUT);
    gpioSetMode(GPIO_OUTPUT_595_DS,       OUTPUT);
    gpioSetMode(GPIO_OUTPUT_595_STCP,     OUTPUT);
    gpioSetMode(GPIO_OUTPUT_595_SHCP,    OUTPUT);
//    gpioSetMode(GPIO_OUTPUT_SPI_SIN,       OUTPUT);
//    gpioSetMode(GPIO_OUTPUT_SPI_SCLK,       OUTPUT);
	gpioSetMode(GPIO_OUTPUT_SPI_CS_7715,       OUTPUT);
	gpioSetMode(GPIO_OUTPUT_SPI_CS_5541,       OUTPUT);

    gpioWrite(GPIO_OUTPUT_DAC714_AA0,    1);//default value
    gpioWrite(GPIO_OUTPUT_DAC714_AA1,       1);
    gpioWrite(GPIO_OUTPUT_595_DS,       0);
    gpioWrite(GPIO_OUTPUT_595_STCP,     0);
    gpioWrite(GPIO_OUTPUT_595_SHCP,    0);
//    gpioWrite(GPIO_OUTPUT_SPI_SIN,       0);
//    gpioWrite(GPIO_OUTPUT_SPI_SCLK,       0);
    gpioWrite(GPIO_OUTPUT_SPI_CS_7715,       1);
    gpioWrite(GPIO_OUTPUT_SPI_CS_5541,       1);

}
/****************************************************************/
void raspiGpioDeInit(void)
{
    gpioWrite(GPIO_OUTPUT_DAC714_AA0,    1);
    gpioWrite(GPIO_OUTPUT_DAC714_AA1,       1);
    gpioWrite(GPIO_OUTPUT_595_DS,       0);
    gpioWrite(GPIO_OUTPUT_595_STCP,     0);
    gpioWrite(GPIO_OUTPUT_595_SHCP,    0);
//    gpioWrite(GPIO_OUTPUT_SPI_SIN,       0);
//    gpioWrite(GPIO_OUTPUT_SPI_SCLK,       0);
    gpioTerminate();
}
#else
void raspiGpioInit(void)
{
    int i;
    int ret = gpioInitialise();

    if (ret<0) {
    	printf("gpio initialise fail\n");
    	return ;
    }
    printf("gpio initialise success:%d\n",ret);
    printf("gpioHardwareRevision:%d\n",raspiGpioHwVer());
    printf("raspiGpioVer:%d\n",raspiGpioVer());

    for(i = 0; i < RASPI_GPIO_SIZE; i++){
    	if(raspiGpio[i].dir == OUTPUT){
    		gpioSetMode(raspiGpio[i].gpio,    OUTPUT);
    		gpioWrite(raspiGpio[i].gpio,       raspiGpio[i].defaultValue);
    	}
    	else if (raspiGpio[i].dir == INPUT){
    		gpioSetMode(raspiGpio[i].gpio,    INPUT);
    		gpioGlitchFilter(raspiGpio[i].gpio,         GPIO_INPUT_STEADY_FILTER);
    	}
    }
}
/****************************************************************/
void raspiGpioDeInit(void)
{
    int i;
    for(i = 0; i < RASPI_GPIO_SIZE; i++){
    	if(raspiGpio[i].dir == OUTPUT){
    		gpioWrite(raspiGpio[i].gpio,       raspiGpio[i].defaultValue);
    	}
    }
    gpioTerminate();
}
#endif





/********************************End Of File********************************/

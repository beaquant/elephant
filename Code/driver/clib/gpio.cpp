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
#include "gpio.h"


/*****************************************************************************
* Define section
* add all #define here
*****************************************************************************/
#define     GPIO_INPUT_STEADY_FILTER        20//ms

/****************************************************************************
* ADT section
*	add definition of user defined Data Type that only be used in this file  here
***************************************************************************/
typedef struct{
    unsigned int gpio;
    unsigned int gpiotrg;
    unsigned int gpiopresstype;
}gpio_read_t;


gpio_read_t gpio_read[MAX_GPIO_INPUT] = {
    {GPIO_INPUT_LEFT,           0, 0},
    {GPIO_INPUT_RIGHT,          0, 0},
    {GPIO_INPUT_UP,             0, 0},
    {GPIO_INPUT_DOWN,           0, 0},
    {GPIO_INPUT_PWM_1_POS,      0, 0},
    {GPIO_INPUT_PWM_2_POS,      0, 0}
};

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
#define GPIO_OUTPUT_DAC714_AA0          2
#define GPIO_OUTPUT_DAC714_AA1          3
#define GPIO_OUTPUT_595_DS              5
#define GPIO_OUTPUT_595_STCP            6
#define GPIO_OUTPUT_595_SHCP            13
#define GPIO_OUTPUT_SPI_SIN             10
#define GPIO_OUTPUT_SPI_SCLK            11

#define GPIO_INPUT_AD7715_DOUT          17
#define GPIO_INPUT_ADS7805_A0           18
#define GPIO_INPUT_ADS7805_A1           23
#define GPIO_INPUT_ADS7805_A2           24
#define GPIO_INPUT_ADS7805_A3           25
#define GPIO_INPUT_ADS7805_A4           12
#define GPIO_INPUT_ADS7805_A5           16
#define GPIO_INPUT_ADS7805_A6           20
#define GPIO_INPUT_ADS7805_A7           21
#define GPIO_INPUT_ADS7805_BUSY         21//TBD

const gpio_t raspiGpio[RASPI_GPIO_SIZE] = {
{GPIO_OUTPUT_DAC714_AA0,        OUTPUT,         HIGH},
{GPIO_OUTPUT_DAC714_AA1,        OUTPUT,         HIGH},
{GPIO_OUTPUT_595_DS,            OUTPUT,         LOW},
{GPIO_OUTPUT_595_STCP,          OUTPUT,         LOW},
{GPIO_OUTPUT_595_SHCP,          OUTPUT,         LOW},
{GPIO_OUTPUT_SPI_SIN,           OUTPUT,         LOW},
{GPIO_OUTPUT_SPI_SCLK,          OUTPUT,         LOW},

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
int raspi_gpio_hw_ver(void)
{
    return gpioHardwareRevision();
}
/****************************************************************/
int raspi_gpio_ver(void)
{
    return gpioVersion();
}
/****************************************************************/
void raspi_gpio_init(void)
{
    if (gpioInitialise()<0) return ;
    /* set gpio input mode */
    gpioSetMode(GPIO_INPUT_AD7715_DOUT,         PI_INPUT);
    gpioSetMode(GPIO_INPUT_ADS7805_A0,          PI_INPUT);
    gpioSetMode(GPIO_INPUT_ADS7805_A1,           PI_INPUT);
    gpioSetMode(GPIO_INPUT_ADS7805_A2,           PI_INPUT);
    gpioSetMode(GPIO_INPUT_ADS7805_A3,           PI_INPUT);
    gpioSetMode(GPIO_INPUT_ADS7805_A4,           PI_INPUT);
    gpioSetMode(GPIO_INPUT_ADS7805_A5,           PI_INPUT);
    gpioSetMode(GPIO_INPUT_ADS7805_A6,           PI_INPUT);
    gpioSetMode(GPIO_INPUT_ADS7805_A7,           PI_INPUT);

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
    gpioSetMode(GPIO_OUTPUT_DAC714_AA0,    PI_OUTPUT);
    gpioSetMode(GPIO_OUTPUT_DAC714_AA1,       PI_OUTPUT);
    gpioSetMode(GPIO_OUTPUT_595_DS,       PI_OUTPUT);
    gpioSetMode(GPIO_OUTPUT_595_STCP,     PI_OUTPUT);
    gpioSetMode(GPIO_OUTPUT_595_SHCP,    PI_OUTPUT);
    gpioSetMode(GPIO_OUTPUT_SPI_SIN,       PI_OUTPUT);
    gpioSetMode(GPIO_OUTPUT_SPI_SCLK,       PI_OUTPUT);

    gpioWrite(GPIO_OUTPUT_DAC714_AA0,    1);//default value
    gpioWrite(GPIO_OUTPUT_DAC714_AA1,       1);
    gpioWrite(GPIO_OUTPUT_595_DS,       0);
    gpioWrite(GPIO_OUTPUT_595_STCP,     0);
    gpioWrite(GPIO_OUTPUT_595_SHCP,    0);
    gpioWrite(GPIO_OUTPUT_SPI_SIN,       0);
    gpioWrite(GPIO_OUTPUT_SPI_SCLK,       0);

}
/****************************************************************/
void raspi_gpio_deinit()
{
    gpioWrite(GPIO_OUTPUT_DAC714_AA0,    1);
    gpioWrite(GPIO_OUTPUT_DAC714_AA1,       1);
    gpioWrite(GPIO_OUTPUT_595_DS,       0);
    gpioWrite(GPIO_OUTPUT_595_STCP,     0);
    gpioWrite(GPIO_OUTPUT_595_SHCP,    0);
    gpioWrite(GPIO_OUTPUT_SPI_SIN,       0);
    gpioWrite(GPIO_OUTPUT_SPI_SCLK,       0);
    gpioTerminate();
}

/********************************End Of File********************************/

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

#ifndef _GPIO_H_
#define _GPIO_H_

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

#define RASPI_GPIO_SIZE               (GPIO_INDEX_TOTAL)

#define HIGH                            1
#define LOW                             0
#define OUTPUT                          1
#define INPUT                           0

/****************************************************************************
* ADT section
*	add Abstract Data Type definition here
***************************************************************************/
typedef struct{
    uint8_t gpio;
    uint8_t dir;
    uint8_t defaultValue;
}gpio_t;

enum{
 GPIO_INDEX_DAC714_AA0 = 0,
 GPIO_INDEX_DAC714_AA1,
 GPIO_INDEX_595_DS    ,
 GPIO_INDEX_595_STCP   ,
 GPIO_INDEX_595_SHCP   ,
 GPIO_INDEX_SPI_SIN  ,
 GPIO_INDEX_SPI_SCLK   ,

 GPIO_INDEX_AD7715_DOUT  ,
 GPIO_INDEX_ADS7805_A0   ,
 GPIO_INDEX_ADS7805_A1   ,
 GPIO_INDEX_ADS7805_A2   ,
 GPIO_INDEX_ADS7805_A3  ,
 GPIO_INDEX_ADS7805_A4  ,
 GPIO_INDEX_ADS7805_A5   ,
 GPIO_INDEX_ADS7805_A6    ,
 GPIO_INDEX_ADS7805_A7     ,
 GPIO_INDEX_ADS7805_BUSY   ,
 GPIO_INDEX_TOTAL

};
/****************************************************************************
*  extern variable declaration section
***************************************************************************/
extern const gpio_t raspiGpio[RASPI_GPIO_SIZE];

/****************************************************************************
*  section
*	add function prototype here if any
***************************************************************************/
#ifdef __cplusplus
}
#endif

#endif


/********************************End Of File********************************/





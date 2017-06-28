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

#ifndef _74HC595_H_
#define _74HC595_H_

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
#define IO_EX_595_BIT0_DAC714_CLR					0
#define IO_EX_595_BIT1_ADS7805_BYTE				1
#define IO_EX_595_BIT2_ADS7805_R_C				2
#define IO_EX_595_BIT3_ADS7805_CS				3
#define IO_EX_595_BIT4_TLC5615_CS					4
#define IO_EX_595_BIT5_SN74F575_SNCLK			5
#define IO_EX_595_BIT6_RESERVED_1				    6
#define IO_EX_595_BIT7_RESERVED						7

#define IO_EX_595_BIT8_SN74F575_PTD0			8
#define IO_EX_595_BIT9_SN74F575_PTD1			9
#define IO_EX_595_BIT10_SN74F575_PTD2			10
#define IO_EX_595_BIT11_SN74F575_PTD3			11
#define IO_EX_595_BIT12_SN74F575_PTD4			12
#define IO_EX_595_BIT13_SN74F575_PTD5			13
#define IO_EX_595_BIT14_SN74F575_PTD6			14
#define IO_EX_595_BIT15_SN74F575_PTD7			15

#define IO_EX_595_DATA_LEN					    16

// #define IO_EX_595_PIN_DS					    5
// #define IO_EX_595_PIN_STCP					    6
// #define IO_EX_595_PIN_SHCP					    13

#define reset595Refresh()				\
                                reset595Buf();update595Output()
#define setAll595Refresh()				\
								setAll595Buf();update595Output()
#define set595Refresh(data)			\
								set595Buf(data);update595Output()
#define setIoRefresh(index)				\
				set595BufByBit(index);update595Output()
#define clrIoRefresh(index)				\
				clr595BufByBit(index);update595Output()
#define ctrlIoRefresh(index, ctrl)		\
				ctrl595BufByBit(index, ctrl);update595Output()
/****************************************************************************
* ADT section
*	add Abstract Data Type definition here
***************************************************************************/


/****************************************************************************
*  extern variable declaration section
***************************************************************************/
void set595BufByBit(uint8_t index);
void clr595BufByBit(uint8_t index);
void ctrl595BufByBit(uint8_t index, uint8_t ctrl);

void update595Output(void);
void reset595Buf();
void setAll595Buf();
void set595Buf(uint16_t data);

uint16_t get595Buf();
void hc595Init(void);


/****************************************************************************
*  section
*	add function prototype here if any
***************************************************************************/
#ifdef __cplusplus
}
#endif

#endif


/********************************End Of File********************************/





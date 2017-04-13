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

#ifndef _SYS_CONFIG_H_
#define _SYS_CONFIG_H_

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
#define MAX_PUMP_NUMBER				15

/****************************************************************************
* ADT section
*	add Abstract Data Type definition here
***************************************************************************/

typedef struct __pumpConfig{
	uint8_t		addr;
	uint16_t	injectCap;
}_pumpConfig;

typedef struct __pumpData{
	_pumpConfig	conf;
	uint16_t			injectRemainCap;
	uint32_t			injectPos;
	uint32_t			valvePos;
}_pumpData;

typedef struct __pump{
	uint8_t					number;
	_pumpData			pumpData[MAX_PUMP_NUMBER];
}_pump;





typedef struct __sysConfig{
	_pump					pump;
}_sysConfig;
/****************************************************************************
*  extern variable declaration section
***************************************************************************/
extern _sysConfig	sysConfig;

/****************************************************************************
*  section
*	add function prototype here if any
***************************************************************************/




#ifdef __cplusplus
}
#endif

#endif


/********************************End Of File********************************/





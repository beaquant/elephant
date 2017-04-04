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

#ifndef _API_H_
#define _API_H_

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

uint8 pumperSetNumber(uint8 number);
/* 设置系统中泵的数量。
参数: number为泵的数量。泵的数量需根据实际情况来设置，即用户在UI界面中输入。
返回值: 0表示ok，其它参见error code。 */

uint8 pumperGetNumber(void);
/* 获取系统中泵的数量。
返回值:泵的数量。 */

uint8 pumperReset(void);
/* 复位泵函数。将泵的注射器活塞移动到顶端0位，泵的阀旋转到位置0位。并且所有的命令参数初始化为默认值。
返回值: 0表示ok，其它参见error code。 */

uint8 pumperInjectReset (void);
/* 复位注射器函数。将泵的注射器活塞移动到顶端0位。
返回值: 0表示ok，其它参见error code。 */

uint8 pumperValveReset (void);
/* 复位阀门函数。泵的阀旋转到位置0位。
返回值: 0表示ok，其它参见error code。 */

uint8 pumperSetInjectCapacity (uint16 ul);
/* 设置注射器容量。
参数:ul 为注射器容量。单位微升(uL), 范围0-L。
返回值: 0表示ok，其它参见error code。 */

uint16 pumperGetInjectCapacity (void);
/* 获取当前注射器容量。
返回值:注射器容量。单位微升(uL), 范围0-L。
 */
uint8 pumperInject(uint16 ul);
/* 注射器注入函数。注入多少微升液体。
参数:ul 为注射器容量。单位微升(uL), 范围0-L。与初始化时输入的容量相关。
返回值: 0表示ok，其它参见error code。 */

uint16 pumperInjectRemain(void);
/* 注射器剩余液体有多少。
返回值: 注射器剩余容量。单位微升(uL), 范围0-L。与初始化时输入的容量相关。
存储在本地文件中记录 */

uint8 pumperInjectorRepair(void);
/* 注射器维护/更换时，使用这个函数。 */


/* 升降台 */
uint8 liftReset (void);
/* 复位升降台函数。将升降台移动到初始位置。
返回值: 0表示ok，其它参见error code。 */

uint8 liftMoveUp(uint16 mm);
/* 升降台向上移动函数。
参数：mm，移动毫米。
返回值: 0表示ok，其它参见error code。 */

uint8 liftMoveDown(uint16 mm);
/* 升降台向下移动函数。
参数：mm，移动毫米。
返回值: 0表示ok，其它参见error code。 */

uint16 liftGetPosition(void);
/* 获取当前升降台相对位置。
返回值: 当前升降台的相对于原点位置，单位毫米。 */

/* 旋转台 */
uint8 turntableStop(void);
/* 停止旋转台旋转函数。
返回值: 0表示ok，其它参见error code。 */

uint8 turntableStart(uin16 rpm);
/* 旋转台以指定的转速旋转函数。
参数: rpm，转速，每分钟多少转。
返回值: 0表示ok，其它参见error code。 */




#ifdef __cplusplus
}
#endif

#endif


/********************************End Of File********************************/





#ifndef _JCONFIG_H_
#define _JCONFIG_H_

#include "stdbool.h"
#include "sys/file.h"
#include <unistd.h>   
#include <fcntl.h>  
#include "pthread.h"
#include "../cJSON-1.4.6/cJSON.h"


#define DEBUG

#ifndef DEBUG

#define CONFIG_FILE_PATH		("/data/config/config.json")
#define CONFIG_FILE_PATH_BACK	("/data/config/config.json~")

#else

#define CONFIG_FILE_PATH        ("config.json")
#define CONFIG_FILE_PATH_BACK	("config.json~")

#endif

#define TYPE_STRING 0
#define TYPE_NUMBER 1
#define TYPE_BOOL 2

typedef struct _configItem
{
	char *root;		//parent node
	char *name;		//obj name
	char type;		//value type:number char bool
	char *string;	//string value
	int  value;		//number and bool value depends "type"
}configItem;

/*
You can add your own config items below.
And this array must end of with {NULL, NULL, -1, NULL, false, 0}
*/

#define ROOT_NAME_VIDEO	"video"
#define ROOT_NAME_AUDIO	"audio"



typedef struct _configManager
{
	cJSON *config;
	pthread_rwlock_t rwlock;
}configManager;

extern configManager *gConfigManager;

configManager *initConfigManager(void);
configManager *getConfigManager(void);
int delConfigManager(configManager *manager);
int saveConfigFiles(configManager *manager);


#endif

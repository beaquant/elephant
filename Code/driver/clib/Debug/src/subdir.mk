################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../src/74hc595.c \
../src/ad7715.c \
../src/ads7805.c \
../src/api.c \
../src/com_slackerOne_JPP.c \
../src/dac714.c \
../src/gpio.c \
../src/logger.c \
../src/pienv.c \
../src/pump.c \
../src/spi.c \
../src/sysconfig.c \
../src/template.c \
../src/tlc5615.c \
../src/version.c 

OBJS += \
./src/74hc595.o \
./src/ad7715.o \
./src/ads7805.o \
./src/api.o \
./src/com_slackerOne_JPP.o \
./src/dac714.o \
./src/gpio.o \
./src/logger.o \
./src/pienv.o \
./src/pump.o \
./src/spi.o \
./src/sysconfig.o \
./src/template.o \
./src/tlc5615.o \
./src/version.o 

C_DEPS += \
./src/74hc595.d \
./src/ad7715.d \
./src/ads7805.d \
./src/api.d \
./src/com_slackerOne_JPP.d \
./src/dac714.d \
./src/gpio.d \
./src/logger.d \
./src/pienv.d \
./src/pump.d \
./src/spi.d \
./src/sysconfig.d \
./src/template.d \
./src/tlc5615.d \
./src/version.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -I/usr/local/include/json-c -I/usr/lib/jvm/java-8-oracle/include -I/usr/lib/jvm/java-8-oracle/include/linux -O0 -g3 -Wall -c -fmessage-length=0 -fPIC -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '



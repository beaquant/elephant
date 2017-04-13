################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../src/cJSON-1.4.6/cJSON.c 

OBJS += \
./src/cJSON-1.4.6/cJSON.o 

C_DEPS += \
./src/cJSON-1.4.6/cJSON.d 


# Each subdirectory must supply rules for building sources it contributes
src/cJSON-1.4.6/%.o: ../src/cJSON-1.4.6/%.c
	@echo 'Building file: $<'
	@echo 'Invoking: Cross GCC Compiler'
	arm-linux-gnueabihf-gcc-4.9 -O0 -g3 -Wall -c -fmessage-length=0 -fPIC -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '



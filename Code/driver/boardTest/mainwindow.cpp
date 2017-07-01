#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "types.h"
#include "74hc595.h"
#include "piconfig.h"
#include "gpio.h"
#include "api.h"
#include "version.h"
#include "tlc5615.h"
#include "spi.h"
#include "ads7805.h"

QString UI_VERSION = "GUI: V1.1.0";
MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    ui->Test_UI_Version->setText(UI_VERSION);
    raspiGpioInit();
    hc595Init();
    raspiSpiInit(0,0);
    ads7805Init();

    on_Get595_clicked();

    int GpioVer = raspiGpioVer();
    QString Ver = QString::number(GpioVer,10);
    ui->gpioVersionText->setText(Ver);
    int GpioHwVer = raspiGpioHwVer();
    QString HwVer = QString::number(GpioHwVer,10);
    ui->HWVersionText->setText(HwVer);
    QString ClibVer = getFirmwareVersion();
    ui->clibVersionText->setText(ClibVer);
//        QString ClibVer = getVersion();
//        ui->clibVersionText->setText(ClibVer);
}

MainWindow::~MainWindow()
{
    delete ui;
    raspiGpioDeInit();
}

void MainWindow::on_HC595PIN0_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT0_DAC714_CLR);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN0_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT0_DAC714_CLR);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN1_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT1_ADS7805_BYTE);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN1_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT1_ADS7805_BYTE);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN2_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT2_ADS7805_R_C);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN2_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT2_ADS7805_R_C);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN3_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT3_ADS7805_CS);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN3_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT3_ADS7805_CS);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN4_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT4_TLC5615_CS);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN4_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT4_TLC5615_CS);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN5_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT5_SN74F575_SNCLK);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN5_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT5_SN74F575_SNCLK);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}
void MainWindow::on_HC595PIN6_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT6_RESERVED_1);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN6_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT6_RESERVED_1);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}
void MainWindow::on_HC595PIN7_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT7_RESERVED);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN7_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT7_RESERVED);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}
void MainWindow::on_HC595PIN8_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT8_SN74F575_PTD0);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN8_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT8_SN74F575_PTD0);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}
void MainWindow::on_HC595PIN9_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT9_SN74F575_PTD1);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN9_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT9_SN74F575_PTD1);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}
void MainWindow::on_HC595PIN10_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT10_SN74F575_PTD2);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN10_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT10_SN74F575_PTD2);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN11_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT11_SN74F575_PTD3);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN11_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT11_SN74F575_PTD3);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}
void MainWindow::on_HC595PIN12_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT12_SN74F575_PTD4);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN12_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT12_SN74F575_PTD4);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN13_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT13_SN74F575_PTD5);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN13_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT13_SN74F575_PTD5);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN14_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT14_SN74F575_PTD6);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN14_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT14_SN74F575_PTD6);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN15_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT15_SN74F575_PTD7);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}

void MainWindow::on_HC595PIN15_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT15_SN74F575_PTD7);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);
}



void MainWindow::on_Reset595_clicked()
{
    reset595Refresh();
    ui->HC595PIN0_0->setChecked(true);
    ui->HC595PIN1_0->setChecked(true);
    ui->HC595PIN2_0->setChecked(true);
    ui->HC595PIN3_0->setChecked(true);
    ui->HC595PIN4_0->setChecked(true);
    ui->HC595PIN5_0->setChecked(true);
    ui->HC595PIN6_0->setChecked(true);
    ui->HC595PIN7_0->setChecked(true);
    ui->HC595PIN8_0->setChecked(true);
    ui->HC595PIN9_0->setChecked(true);
    ui->HC595PIN10_0->setChecked(true);
    ui->HC595PIN11_0->setChecked(true);
    ui->HC595PIN12_0->setChecked(true);
    ui->HC595PIN13_0->setChecked(true);
    ui->HC595PIN14_0->setChecked(true);
    ui->HC595PIN15_0->setChecked(true);
    int dat = get595Buf();
    QString s;
    s.sprintf("0x%x\n", dat);
    ui->DISPLAY_74HC595->setText(s);

}

void MainWindow::on_Get595_clicked()
{
    uint16_t dat = get595Buf();
    if(dat & 0x0001){
        ui->HC595PIN0_1->setChecked(true);
    }
    else{
        ui->HC595PIN0_0->setChecked(true);
    }
    if(dat & 0x0002){
        ui->HC595PIN1_1->setChecked(true);
    }
    else{
        ui->HC595PIN1_0->setChecked(true);
    }
    if(dat & 0x0004){
        ui->HC595PIN2_1->setChecked(true);
    }
    else{
        ui->HC595PIN2_0->setChecked(true);
    }
    if(dat & 0x0008){
        ui->HC595PIN3_1->setChecked(true);
    }
    else{
        ui->HC595PIN3_0->setChecked(true);
    }
    if(dat & 0x0010){
        ui->HC595PIN4_1->setChecked(true);
    }
    else{
        ui->HC595PIN4_0->setChecked(true);
    }
    if(dat & 0x0020){
        ui->HC595PIN5_1->setChecked(true);
    }
    else{
        ui->HC595PIN5_0->setChecked(true);
    }
    if(dat & 0x0040){
        ui->HC595PIN6_1->setChecked(true);
    }
    else{
        ui->HC595PIN6_0->setChecked(true);
    }
    if(dat & 0x0080){
        ui->HC595PIN7_1->setChecked(true);
    }
    else{
        ui->HC595PIN7_0->setChecked(true);
    }
    if(dat & 0x0100){
        ui->HC595PIN8_1->setChecked(true);
    }
    else{
        ui->HC595PIN8_0->setChecked(true);
    }
    if(dat & 0x0200){
        ui->HC595PIN9_1->setChecked(true);
    }
    else{
        ui->HC595PIN9_0->setChecked(true);
    }
    if(dat & 0x0400){
        ui->HC595PIN10_1->setChecked(true);
    }
    else{
        ui->HC595PIN10_0->setChecked(true);
    }
    if(dat & 0x0800){
        ui->HC595PIN11_1->setChecked(true);
    }
    else{
        ui->HC595PIN11_0->setChecked(true);
    }
    if(dat & 0x1000){
        ui->HC595PIN12_1->setChecked(true);
    }
    else{
        ui->HC595PIN12_0->setChecked(true);
    }
    if(dat & 0x2000){
        ui->HC595PIN13_1->setChecked(true);
    }
    else{
        ui->HC595PIN13_0->setChecked(true);
    }
    if(dat & 0x4000){
        ui->HC595PIN14_1->setChecked(true);
    }
    else{
        ui->HC595PIN14_0->setChecked(true);
    }
    if(dat & 0x8000){
        ui->HC595PIN15_1->setChecked(true);
    }
    else{
        ui->HC595PIN15_0->setChecked(true);
    }
}
/**/

//void MainWindow::on_Get595_clicked()
//{

//}

void MainWindow::on_STCP_0_clicked()
{
    clrOutput(6);
}

void MainWindow::on_STCP_1_clicked()
{
     setOutput(6);
}

void MainWindow::on_SHCP_0_clicked()
{
    clrOutput(13);
}

void MainWindow::on_SHCP_1_clicked()
{
    setOutput(13);
}

void MainWindow::on_DS_0_clicked()
{
    clrOutput(5);
}

void MainWindow::on_DS_1_clicked()
{
    setOutput(5);
}

void MainWindow::on_Set0xaaaa_clicked()
{
    set595Refresh(0xaaaa);
}



void MainWindow::on_Update_to_U6_7_clicked()
{
    int a = ui->TCL5615_U6->value();
    int b = ui->TCL5615_U7->value();
    QString s;
    s.sprintf("a=%d, b=%d\n", a,b);
    ui->debugInfo->setText(s);
    tlc5615Set(a,b);
}

void MainWindow::on_DisableSPI_stateChanged(int arg1)
{
    QString s;
    s.sprintf("arg1=%d\n", arg1);
    ui->debugInfo->setText(s);
    if(arg1 == 0){
        raspiSpiInit(0,0);
    }
    else if(arg1 == 2){
        raspiSpiDeInit();
        raspiGpioSetMode(GPIO_OUTPUT_SPI_SIN,       OUTPUT);
        raspiGpioSetMode(GPIO_OUTPUT_SPI_SCLK,       OUTPUT);
    }
}

void MainWindow::on_OUTPUT_0_clicked()
{
    int gpio = ui->GPIO->value();
    clrOutput(gpio);
    QString s;
    s.sprintf("gpio%d clear\n", gpio);
    ui->debugInfo->setText(s);

}

void MainWindow::on_OUTPUT_1_clicked()
{
    int gpio = ui->GPIO->value();
    setOutput(gpio);
    QString s;
    s.sprintf("gpio%d set\n", gpio);
    ui->debugInfo->setText(s);
}

void MainWindow::on_Start7805_clicked()
{
    int ret = ads7805Start();
    if(ret == ADS7805_OK){
        ui->ADS7805Status->setText("ADS7805 started to conversion");
    }else{
        ui->ADS7805Status->setText("ADS7805 busy");
    }
}

void MainWindow::on_Get7805Result_clicked()
{
    uint16_t result = 0;
    int ret = ads7805Result(&result);
    if(ret == ADS7805_OK){
        ui->ADS7805Status->setText("ADS7805 conversion finished");
        QString s;
        s.sprintf("%d", result);
        ui->ADS7805Result->setText(s);
    }else{
        ui->ADS7805Status->setText("ADS7805 busy");
    }

}

void MainWindow::on_Stop7805_clicked()
{
    int ret = ads7805Stop();
    if(ret == ADS7805_OK){
        ui->ADS7805Status->setText("ADS7805 stop");
    }else{
        ui->ADS7805Status->setText("ADS7805 busy");
    }

}

void MainWindow::on_ReaGpio_clicked()
{
    int gpio = ui->GPIO_2->value();
    int dat = getInput(gpio);
    int mode = getGpioMode(gpio);
    if(dat == 0){
        ui->INPUT_0->setChecked(true);
//        ui->INPUT_1->setChecked(false);
    }
    else{
        ui->INPUT_1->setChecked(true);
//        ui->INPUT_0->setChecked(false);

    }

    QString s;
    s.sprintf("gpio%d = %d, mode=%d\n", gpio, dat, mode);
    ui->debugInfo->setText(s);

}

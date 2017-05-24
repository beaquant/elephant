#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "types.h"
#include "74hc595.h"
#include "piconfig.h"
#include "gpio.h"
#include "api.h"
#include "version.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    raspiGpioInit();
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
}

void MainWindow::on_HC595PIN0_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT0_DAC714_CLR);
}

void MainWindow::on_HC595PIN1_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT1_ADS7805_BYTE);
}

void MainWindow::on_HC595PIN1_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT1_ADS7805_BYTE);
}

void MainWindow::on_HC595PIN2_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT2_ADS7805_R_C);
}

void MainWindow::on_HC595PIN2_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT2_ADS7805_R_C);
}

void MainWindow::on_HC595PIN3_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT3_ADS7805_CS);
}

void MainWindow::on_HC595PIN3_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT3_ADS7805_CS);
}

void MainWindow::on_HC595PIN4_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT4_TLC5615_CS);
}

void MainWindow::on_HC595PIN4_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT4_TLC5615_CS);
}

void MainWindow::on_HC595PIN5_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT5_SN74F575_SNCLK);
}

void MainWindow::on_HC595PIN5_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT5_SN74F575_SNCLK);
}
void MainWindow::on_HC595PIN6_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT6_RESERVED_BUSY);
}

void MainWindow::on_HC595PIN6_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT6_RESERVED_BUSY);
}
void MainWindow::on_HC595PIN7_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT7_RESERVED);
}

void MainWindow::on_HC595PIN7_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT7_RESERVED);
}
void MainWindow::on_HC595PIN8_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT8_SN74F575_PTD0);
}

void MainWindow::on_HC595PIN8_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT8_SN74F575_PTD0);
}
void MainWindow::on_HC595PIN9_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT9_SN74F575_PTD1);
}

void MainWindow::on_HC595PIN9_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT9_SN74F575_PTD1);
}
void MainWindow::on_HC595PIN10_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT10_SN74F575_PTD2);
}

void MainWindow::on_HC595PIN10_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT10_SN74F575_PTD2);
}

void MainWindow::on_HC595PIN11_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT11_SN74F575_PTD3);
}

void MainWindow::on_HC595PIN11_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT11_SN74F575_PTD3);
}
void MainWindow::on_HC595PIN12_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT12_SN74F575_PTD4);
}

void MainWindow::on_HC595PIN12_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT12_SN74F575_PTD4);
}

void MainWindow::on_HC595PIN13_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT13_SN74F575_PTD5);
}

void MainWindow::on_HC595PIN13_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT13_SN74F575_PTD5);
}

void MainWindow::on_HC595PIN14_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT14_SN74F575_PTD6);
}

void MainWindow::on_HC595PIN14_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT14_SN74F575_PTD6);
}

void MainWindow::on_HC595PIN15_0_clicked()
{
    clrIoRefresh(IO_EX_595_BIT15_SN74F575_PTD7);
}

void MainWindow::on_HC595PIN15_1_clicked()
{
    setIoRefresh(IO_EX_595_BIT15_SN74F575_PTD7);
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

}
/*
void MainWindow::on_Get595_clicked()
{
    uint16_t dat = get595Buf();
    if(dat & 0x0001){
        ui->HC595PIN0_1->setChecked(true);
    }
    else{
        ui->HC595PIN0_0->setChecked(true);
    }
    if(dat & 0x0001){
        ui->HC595PIN0_1->setChecked(true);
    }
    else{
        ui->HC595PIN0_0->setChecked(true);
    }
    if(dat & 0x0001){
        ui->HC595PIN0_1->setChecked(true);
    }
    else{
        ui->HC595PIN0_0->setChecked(true);
    }
    if(dat & 0x0001){
        ui->HC595PIN0_1->setChecked(true);
    }
    else{
        ui->HC595PIN0_0->setChecked(true);
    }
    if(dat & 0x0001){
        ui->HC595PIN0_1->setChecked(true);
    }
    else{
        ui->HC595PIN0_0->setChecked(true);
    }    if(dat & 0x0001){
        ui->HC595PIN0_1->setChecked(true);
    }
    else{
        ui->HC595PIN0_0->setChecked(true);
    }    if(dat & 0x0001){
        ui->HC595PIN0_1->setChecked(true);
    }
    else{
        ui->HC595PIN0_0->setChecked(true);
    }
    if(dat & 0x0001){
        ui->HC595PIN0_1->setChecked(true);
    }
    else{
        ui->HC595PIN0_0->setChecked(true);
    }    if(dat & 0x0001){
        ui->HC595PIN0_1->setChecked(true);
    }
    else{
        ui->HC595PIN0_0->setChecked(true);
    }    if(dat & 0x0001){
        ui->HC595PIN0_1->setChecked(true);
    }
    else{
        ui->HC595PIN0_0->setChecked(true);
    }    if(dat & 0x0001){
        ui->HC595PIN0_1->setChecked(true);
    }
    else{
        ui->HC595PIN0_0->setChecked(true);
    }    if(dat & 0x0001){
        ui->HC595PIN0_1->setChecked(true);
    }
    else{
        ui->HC595PIN0_0->setChecked(true);
    }
    if(dat & 0x0001){
        ui->HC595PIN0_1->setChecked(true);
    }
    else{
        ui->HC595PIN0_0->setChecked(true);
    }

}
*/

void MainWindow::on_Get595_clicked()
{

}

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

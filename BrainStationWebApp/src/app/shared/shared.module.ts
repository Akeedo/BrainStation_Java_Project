import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RedOnEmptyDirective } from './directives/red-on-empty.directive';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RouterModule } from '@angular/router';
import { BarChartComponent } from './components/bar-chart/bar-chart.component';
import { LineChartComponent } from './components/line-chart/line-chart.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { ToastModule } from 'primeng/toast';
import { CalendarModule } from 'primeng/calendar';
@NgModule({
  declarations: [
    NavbarComponent,
    RedOnEmptyDirective,
    BarChartComponent,
    LineChartComponent,
    BarChartComponent
  ],
  imports: [
    ToastModule,
    CommonModule,
    RouterModule,
    ButtonModule, 
    InputTextModule,
    CalendarModule
     
  ],
  exports: [
    NavbarComponent,
    BarChartComponent,
    RedOnEmptyDirective,
    RouterModule, 
    ButtonModule, 
    InputTextModule,
    MessagesModule,
    MessageModule,
    ToastModule,
    CalendarModule
  ]
})
export class SharedModule { }

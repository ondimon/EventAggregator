import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './components/menu/menu.component';
import { EventsComponent } from './components/events/events.component';
import { MainComponent } from './components/main/main.component';
import {HttpClientModule} from "@angular/common/http";
import { GbEventsComponent } from './components/events/gb-events/gb-events.component';
import { SbEventsComponent } from './components/events/sb-events/sb-events.component';
import { SfEventsComponent } from './components/events/yandex-events/sf-events.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    EventsComponent,
    MainComponent,
    GbEventsComponent,
    SbEventsComponent,
    SfEventsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

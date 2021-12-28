import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EventsComponent} from "./components/events/events.component";
import {GbEventsComponent} from "./components/events/gb-events/gb-events.component";
import {SbEventsComponent} from "./components/events/sb-events/sb-events.component";
import {YandexEventsComponent} from "./components/events/yandex-events/yandex-events.component";

/**
 * Список роутов
 */
const routes: Routes = [
  {path: '', component: EventsComponent},
  {path: 'gb', component: GbEventsComponent},
  {path: 'sb', component: SbEventsComponent},
  {path: 'yandex', component: YandexEventsComponent}
];

/**
 * Настройки роутинга приложения
 */
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

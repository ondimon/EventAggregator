import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EventsComponent} from "./components/events/events.component";
import {GbEventsComponent} from "./components/events/gb-events/gb-events.component";
import {SbEventsComponent} from "./components/events/sb-events/sb-events.component";
import {SfEventsComponent} from "./components/events/yandex-events/sf-events.component";

/**
 * Список роутов
 */
const routes: Routes = [
  {path: '', component: EventsComponent},
  {path: 'gb', component: GbEventsComponent},
  {path: 'sb', component: SbEventsComponent},
  {path: 'sf', component: SfEventsComponent}
];

/**
 * Настройки роутинга приложения
 */
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { Component, OnInit } from '@angular/core';
import {IEvent} from "../../model/IEvent";
import {HttpService} from "../../services/http.service";
import {MenuService} from "../../services/menu.service";

/**
 * Компонент отображения списка событий
 */
@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})
export class EventsComponent implements OnInit {

  /**
   * Список событий
   */
  events: IEvent[] = [];

  /**
   * Конструктор
   *
   * @param httpService Сервис взаимодействия с сервером
   * @param menuService Сервис смены логотипа меню
   */
  constructor(private httpService: HttpService,
              private menuService: MenuService) { }

  /**
   * Инициализация компонента,
   * получение с сервера списка событий
   */
  ngOnInit(): void {
    this.menuService.setLogo('logo.jpg');
    this.httpService.getAllEvents().subscribe(result => {
      this.events = result;
    });
  }
}

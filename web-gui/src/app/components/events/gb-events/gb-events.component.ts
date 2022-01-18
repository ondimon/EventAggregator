import { Component, OnInit } from '@angular/core';
import {IEvent} from "../../../model/IEvent";
import {HttpService} from "../../../services/http.service";
import {MenuService} from "../../../services/menu.service";

/**
 * Компонент отображения списка событий портала GeekBrains
 */
@Component({
  selector: 'app-gb-events',
  templateUrl: './gb-events.component.html',
  styleUrls: ['./gb-events.component.scss']
})
export class GbEventsComponent implements OnInit {

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
    this.menuService.setLogo('gb-orig.jpg');
    this.httpService.getAllEvents().subscribe(result => {
      this.events = result.filter(item => item.link.startsWith('https://gb.ru/'));
    });
  }
}

import { Component, OnInit } from '@angular/core';
import {IEvent} from "../../../model/IEvent";
import {HttpService} from "../../../services/http.service";
import {MenuService} from "../../../services/menu.service";

/**
 * Компонент отображения списка событий портала SkillBox
 */
@Component({
  selector: 'app-sb-events',
  templateUrl: './sb-events.component.html',
  styleUrls: ['./sb-events.component.scss']
})
export class SbEventsComponent implements OnInit {

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
    this.menuService.setLogo('sb-orig.png');
    this.httpService.getAllEvents().subscribe(result => {
      this.events = result.filter(item => item.link.startsWith('https://live.skillbox.ru/'));
    });
  }
}

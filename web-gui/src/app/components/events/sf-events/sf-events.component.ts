import { Component, OnInit } from '@angular/core';
import {IEvent} from "../../../model/IEvent";
import {HttpService} from "../../../services/http.service";
import {MenuService} from "../../../services/menu.service";

/**
 * Компонент отображения списка событий портала SkillFactory
 */
@Component({
  selector: 'app-sf-events',
  templateUrl: './sf-events.component.html',
  styleUrls: ['./sf-events.component.scss']
})
export class SfEventsComponent implements OnInit {

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
    this.menuService.setLogo('sf-orig.jpg');
    this.httpService.getAllEvents().subscribe(result => {
      this.events = result.filter(item => item.link.startsWith('https://live.skillfactory.ru/'));
    });
  }
}

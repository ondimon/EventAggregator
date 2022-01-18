import { Component, OnInit } from '@angular/core';
import {IEvent} from "../../../model/IEvent";
import {HttpService} from "../../../services/http.service";

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
   */
  constructor(private httpService: HttpService) { }

  /**
   * Инициализация компонента,
   * получение с сервера списка событий
   */
  ngOnInit(): void {
    this.httpService.getAllEvents().subscribe(result => {
      this.events = result.filter(item => item.link.startsWith('https://live.skillfactory.ru/'));
    });
  }
}

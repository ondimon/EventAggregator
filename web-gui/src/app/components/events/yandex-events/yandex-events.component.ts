import { Component, OnInit } from '@angular/core';
import {IEvent} from "../../../model/IEvent";
import {HttpService} from "../../../services/http.service";

/**
 * Компонент отображения списка событий портала Yandex
 */
@Component({
  selector: 'app-yandex-events',
  templateUrl: './yandex-events.component.html',
  styleUrls: ['./yandex-events.component.scss']
})
export class YandexEventsComponent implements OnInit {

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
      this.events = result.filter(item => item.link.startsWith('https://yandex.ru/'));
    });
  }
}

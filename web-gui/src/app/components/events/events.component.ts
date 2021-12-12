import { Component, OnInit } from '@angular/core';
import {IEvent} from "../../model/IEvent";
import {HttpService} from "../../services/http.service";

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
   */
  constructor(private httpService: HttpService) { }

  /**
   * Инициализация компонента,
   * получение с сервера списка событий
   */
  ngOnInit(): void {
    this.httpService.getAllEvents().subscribe(result => {
      this.events = result;
    });
  }
}

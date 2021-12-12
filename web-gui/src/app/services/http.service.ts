import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {IEvent} from "../model/IEvent";

/**
 * Сервис взаимодействия с сервером
 */
@Injectable({
  providedIn: 'root'
})
export class HttpService {

  /**
   * Адрес сервера
   */
  private apiUrl: string = environment.apiUrl;

  /**
   * Конструктор
   *
   * @param http Клиент для http-запросов
   */
  constructor(private http: HttpClient) {}

  /**
   * Получение с сервера списка событий
   */
  public getAllEvents(): Observable<IEvent[]> {
    return this.http.get<IEvent[]>(this.apiUrl + '/events');
  }
}

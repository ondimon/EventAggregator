import { Injectable } from '@angular/core';
import {Subject} from "rxjs";

/**
 * Сервис смены логотипа меню
 */
@Injectable({
  providedIn: 'root'
})
export class MenuService {

  /**
   * Путь к файлу логотипа меню
   */
  logo$: Subject<string> = new Subject();

  /**
   * Сеттер пути к файлу логотипа меню
   *
   * @param logoFileName имя файла логотипа с расширением, без указания пути
   * (файл должен быть помещён в каталог assets/logo
   */
  public setLogo(logoFileName: string): void {
    this.logo$.next('assets/logo/' + logoFileName);
  }
}

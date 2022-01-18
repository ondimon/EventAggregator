import { Component, OnInit } from '@angular/core';
import {MenuService} from "../../services/menu.service";

/**
 * Компонент отображения меню приложения
 */
@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  /**
   * Путь к файлу логотипа меню
   */
  logo: string = "";

  /**
   * Конструктор
   *
   * @param menuService Сервис смены логотипа меню
   */
  constructor(private menuService: MenuService) {
  }

  /**
   * Инициализация компонента, подписка на изменение
   * пути к файлу логотипа в меню
   */
  ngOnInit(): void {
    this.menuService.logo$.subscribe(result => {
      this.logo = result;
    })
  }
}

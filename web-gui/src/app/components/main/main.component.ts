import { Component, OnInit } from '@angular/core';

/**
 * Главный компонент приложения,
 * служащий контейнером для других компонентов
 */
@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
}

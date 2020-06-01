import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INotificationInfo } from 'app/shared/model/notification-info.model';

@Component({
  selector: 'jhi-notification-info-detail',
  templateUrl: './notification-info-detail.component.html',
})
export class NotificationInfoDetailComponent implements OnInit {
  notificationInfo: INotificationInfo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ notificationInfo }) => (this.notificationInfo = notificationInfo));
  }

  previousState(): void {
    window.history.back();
  }
}

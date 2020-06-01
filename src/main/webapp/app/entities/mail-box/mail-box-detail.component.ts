import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMailBox } from 'app/shared/model/mail-box.model';

@Component({
  selector: 'jhi-mail-box-detail',
  templateUrl: './mail-box-detail.component.html',
})
export class MailBoxDetailComponent implements OnInit {
  mailBox: IMailBox | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mailBox }) => (this.mailBox = mailBox));
  }

  previousState(): void {
    window.history.back();
  }
}

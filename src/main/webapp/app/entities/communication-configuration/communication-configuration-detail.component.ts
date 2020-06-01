import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICommunicationConfiguration } from 'app/shared/model/communication-configuration.model';

@Component({
  selector: 'jhi-communication-configuration-detail',
  templateUrl: './communication-configuration-detail.component.html',
})
export class CommunicationConfigurationDetailComponent implements OnInit {
  communicationConfiguration: ICommunicationConfiguration | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ communicationConfiguration }) => (this.communicationConfiguration = communicationConfiguration));
  }

  previousState(): void {
    window.history.back();
  }
}

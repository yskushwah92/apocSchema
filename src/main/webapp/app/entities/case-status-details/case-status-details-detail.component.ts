import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICaseStatusDetails } from 'app/shared/model/case-status-details.model';

@Component({
  selector: 'jhi-case-status-details-detail',
  templateUrl: './case-status-details-detail.component.html',
})
export class CaseStatusDetailsDetailComponent implements OnInit {
  caseStatusDetails: ICaseStatusDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ caseStatusDetails }) => (this.caseStatusDetails = caseStatusDetails));
  }

  previousState(): void {
    window.history.back();
  }
}

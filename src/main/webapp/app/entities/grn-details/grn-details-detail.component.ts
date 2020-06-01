import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGRNDetails } from 'app/shared/model/grn-details.model';

@Component({
  selector: 'jhi-grn-details-detail',
  templateUrl: './grn-details-detail.component.html',
})
export class GRNDetailsDetailComponent implements OnInit {
  gRNDetails: IGRNDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ gRNDetails }) => (this.gRNDetails = gRNDetails));
  }

  previousState(): void {
    window.history.back();
  }
}

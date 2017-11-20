import { Component, OnInit } from '@angular/core';
import { DealsComponent } from '../deals/deals.component';
import {ApiService} from '../api.service';
import { Router } from '@angular/router';
import { OnDestroy } from '@angular/core/src/metadata/lifecycle_hooks';
import { Hotel } from '../model';

@Component({
  selector: 'app-hotels',
  templateUrl: '../deals/deals.component.html',
  styleUrls: ['./hotels.component.css']
})
export class HotelsComponent extends DealsComponent implements OnInit, OnDestroy {
  
  constructor(protected apiService: ApiService, protected router: Router) { super(apiService, router) }
  ngOnInit() {
    super.ngOnInit();
  }
  
  public retrieveOffers(): void {
    this.subscription = this.apiService.getHotels().subscribe(
      deals => this.setOffers(deals)
    );
   }
}

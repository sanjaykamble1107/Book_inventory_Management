import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { PurchaseLogService } from 'src/app/service/purchase-log.service';
@Component({
  selector: 'app-get-purchaselog',
  templateUrl: './getPurchaseLog.component.html',
  styleUrls: ['./getPurchaseLog.component.css']
})
export class GetPurchaseLogComponent {
  userId: number = 0;
  purchaseLogs: any = {
    userId: "",
    inventoryId: ""
  };

  flag: boolean = false;
  constructor(private purchaseLogService: PurchaseLogService) { }

  searchPurchaseLogs() {
    this.purchaseLogService.getPurchaseLogsByUserId(this.userId)
      .subscribe((response: any) => {
        this.purchaseLogs = response;
        this.flag = true;
      }, (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      });
  }
}

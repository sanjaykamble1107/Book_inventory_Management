import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { PurchaseLogService } from 'src/app/service/purchase-log.service';
@Component({
  selector: 'app-update-purchaselog',
  templateUrl: './update-purchase-log.component.html',
  styleUrls: ['./update-purchase-log.component.css']
})
export class UpdatePurchaseLogComponent {
  userId: number = 0;
  inventoryId: number = 0;
  updateSuccess: boolean = false;
  updateError: boolean = false;

  constructor(private purchaseLogService: PurchaseLogService) { }

  updatePurchaseLog() {
    this.purchaseLogService.updatePurchaseLog(this.userId, this.inventoryId)
      .subscribe(() => {
        this.updateSuccess = true;
        this.updateError = false;
      }, (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);

      });
  }
}

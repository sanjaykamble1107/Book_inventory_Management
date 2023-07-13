import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatePurchaseLogComponent } from './update-purchase-log.component';

describe('UpdatePurchaseLogComponent', () => {
  let component: UpdatePurchaseLogComponent;
  let fixture: ComponentFixture<UpdatePurchaseLogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdatePurchaseLogComponent]
    });
    fixture = TestBed.createComponent(UpdatePurchaseLogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

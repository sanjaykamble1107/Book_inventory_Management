import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetPurchaseLogComponent } from './getpurchaselog.component';

describe('GetpurchaselogComponent', () => {
  let component: GetPurchaseLogComponent;
  let fixture: ComponentFixture<GetPurchaseLogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetPurchaseLogComponent]
    });
    fixture = TestBed.createComponent(GetPurchaseLogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

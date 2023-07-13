import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddbookconditionComponent } from './addbookcondition.component';

describe('AddbookconditionComponent', () => {
  let component: AddbookconditionComponent;
  let fixture: ComponentFixture<AddbookconditionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddbookconditionComponent]
    });
    fixture = TestBed.createComponent(AddbookconditionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

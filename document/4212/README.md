```typescript
    <span [ngSwitch]="toeChoice">

      <!-- with *NgSwitch -->
      <span *ngSwitchCase="'Eenie'">Eenie</span>
      <span *ngSwitchCase="'Meanie'">Meanie</span>
      <span *ngSwitchCase="'Miney'">Miney</span>
      <span *ngSwitchCase="'Moe'">Moe</span>
      <span *ngSwitchDefault>other</span>

      <!-- with <template> -->
      <template [ngSwitchCase]="'Eenie'"><span>Eenie</span></template>
      <template [ngSwitchCase]="'Meanie'"><span>Meanie</span></template>
      <template [ngSwitchCase]="'Miney'"><span>Miney</span></template>
      <template [ngSwitchCase]="'Moe'"><span>Moe</span></template>
      <template ngSwitchDefault><span>other</span></template>

    </span>
```

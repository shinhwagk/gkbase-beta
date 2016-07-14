```typescript
export class KeyUpComponent_v1 {
  values = '';

  // without strong typing
  onKey(event:any) {
    this.values += event.target.value + ' | ';
  }
}
```

### 强类型版本
```typescript
export class KeyUpComponent_v1 {
  values = '';

  // with strong typing
  onKey(event: KeyboardEvent) {
    this.values += (<HTMLInputElement>event.target).value + ' | ';
  }
}
```

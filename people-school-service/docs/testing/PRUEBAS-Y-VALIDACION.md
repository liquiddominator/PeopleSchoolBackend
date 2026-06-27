# Pruebas y verificacion

## Suite automatizada

Ejecutar desde `people-school-service`:

```powershell
.\mvnw.cmd test
```

La suite incluye:

- arranque del contexto Spring;
- pruebas unitarias de dominio y casos de uso;
- pruebas de seguridad JWT y tenancy;
- pruebas de integracion de persistencia/controladores existentes;
- ArchUnit para limites del monolito modular.

## Calidad de formato

```powershell
.\mvnw.cmd spotless:check
```

Para aplicar el formato configurado:

```powershell
.\mvnw.cmd spotless:apply
```

## Verificaciones minimas por cambio

| Cambio | Cobertura esperada |
|---|---|
| Regla de dominio | Prueba unitaria del caso valido y de la invariancia violada. |
| Endpoint | Autorizacion, validacion, status HTTP, tenant y respuesta. |
| Repositorio/migracion | Persistencia con FK, unicidad e indices relevantes. |
| JWT o scopes | Token valido, claim faltante, rol/scope insuficiente y revocacion. |
| Dependencia entre modulos | ArchUnit y prueba del puerto/adaptador. |

## Validacion manual de API

Con PostgreSQL, Redis e IAM activos:

1. Obtener un access token de IAM.
2. Abrir `/swagger-ui.html` o consultar `/v3/api-docs`.
3. Enviar `Authorization: Bearer ...`.
4. Para admin global, probar con y sin `X-Tenant-Id`.
5. Confirmar que un token de tenant no puede leer datos de otro tenant.

## Base limpia

Los cambios Flyway deben verificarse tanto creando un schema desde cero como actualizando uno que ya tenga la version anterior. El TXT consolidado es documental; las pruebas normales deben ejecutar las migraciones individuales.

## Riesgos de cobertura actuales

La estructura contiene carpetas previstas para pruebas contractuales y adaptadores futuros, pero no toda operacion REST tiene necesariamente una prueba end-to-end individual. Al ampliar capitulos 2 y 3, la cobertura debe crecer junto con cada caso de uso y no depender solo del arranque del contexto.

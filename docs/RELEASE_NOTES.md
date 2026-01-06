# ApexOne Core Backend – Release Notes

## Version
v1.0.0

## Release Type
Initial production-grade backend release

## Scope
This release completes **Phases 1–8** of the ApexOne Bank Core Backend.

Included capabilities:
- Customer onboarding (CIF-based)
- Account opening workflows
- Staging → validation → master promotion
- Status-driven processing lifecycle
- Error handling and rollback paths
- Idempotent service execution
- Audit-friendly data persistence

## Supported Modules
- Savings Account (SB)
- Current Account (CA)
- Core Customer Data
- Account Scheme & Interest Configuration
- Nominee & Document Handling (where applicable)

## Processing Lifecycle
- R → Record received
- P → Processing
- S → Successfully completed
- X → Failed with reason captured

## Non-Goals (Explicitly Out of Scope)
- UI rendering or presentation logic
- Direct database access for consumers
- Authentication / authorization UI flows
- Reporting dashboards
- External payment integrations

## Known Limitations
- UI must strictly follow API contracts
- Backend assumes validated request payloads
- Retry logic is service-driven, not UI-driven

## Upgrade Notes
- This is the baseline release
- Breaking changes will require a major version bump (v2.x.x)

## Tag
git tag: v1.0.0

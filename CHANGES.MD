3.3.0 (2020-01-07)
=================
- Add support for `$client_language` field to `$app` complex field
- Add support for `$accept_language` and `$content_language` fields to `$browser` complex field
- Add support for `$brand_name`, `$site_country` and `$site_domain` fields to custom events and all reserved events except `$chargeback`, `$link_session_to_user` and `$flag_content`
- Add support for `$ordered_from` complex field to `$create_order`, `$update_order`, and `$transaction` events

3.2.0 (2019-07-31)
=================
- Add support for `$tags` field to `$booking` complex field

3.1.0 (2019-07-17)
=================
- Add support for `$bookings` complex field to `$create_order` and `$update_order` events

3.0.0 (2019-05-14)
=================
- Add account id and OkHttpClient to constructor
- Add Circle build

2.7.0 (2019-02-18)
=================
- Add support for `$app` and `$browser` to `$content_status`, `$order_status`, and `$security_notification`

2.6.0 (2019-01-30)
=================

- Add support for `$account_types` to the `$create_account` event and the `$update_account` event
- Add support for the new event `$update_password`
- Add support for `$verified_event`, `$reason`, `$verified_entity_id` to the `$verification` event
- Add support for `$ip`, `$browser`, `$app` to the `$verification` event

2.5.0 (2019-01-09)
=================

- Add support for `$failure_reason` to the `$login` event
- Add support for `$account_types` to the `$login` event
- Add support for `$username` to the `$login` event
- Add support for `$social_sign_on_type` to the `$login` event

2.4.0 (2018-10-24)
=================
- Add support for Rescore User and Get User Score APIs
- Add support for `$app` and `$browser` to the following events: `AddItemToCart`, `AddPromotion`, `CreateAccount`, `CreateContent`, `CreateOrder`, `CustomEvent`, `Logout`, `RemoveItemFromCart`, `Transaction`, `UpdateAccount`, and `UpdateContent`
- Upgrade okhttp3 to 3.4.2

2.3.0 (2018-08-08)
=================

- Add support for Rescore User and Get User Score APIs

2.2.1 (2018-08-06)
=================

- Remove Guava dependency
- Replace use of internal Sun API class

2.2.0 (2018-07-05)
=================

- Add new query parameter force_workflow_run

2.1.0 (2018-06-04)
=================

- Adds support for session decisions to [Decisions API](https://siftscience.com/developers/docs/curl/decisions-api)

2.0.1 (2018-04-06)
=================

- Improved documentation in CHANGES.MD and README.md.
- Added missing fields to Message.java
- Removed deprecated files.

2.0 (2018-03-30)
=================

- Adds support for Sift Science API Version 205, including new [`$create_content`](https://siftscience.com/developers/docs/curl/events-api/reserved-events/create-content) and [`$update_content`](https://siftscience.com/developers/docs/curl/events-api/reserved-events/update-content) formats
- V205 APIs are now called -- **this is an incompatible change**
- Adds support for content decisions to [Decisions API](https://siftscience.com/developers/docs/curl/decisions-api)


INCOMPATIBLE CHANGES INTRODUCED IN API V205:
- `$create_content` and `$update_content` have significantly changed, and the old format will be rejected
- `$send_message` and `$submit_review` events are no longer valid
- V205 improves server-side event data validation. In V204 and earlier, server-side validation accepted some events that did not conform to the published APIs in our [developer documentation](https://siftscience.com/developers/docs/curl/events-api). V205 does not modify existing event APIs other than those mentioned above, but may reject invalid event data that were previously accepted. **Please test your integration on V205 in sandbox before using in production.**


1.5 (2018-02-09)
=================

- Add session level decisions in ApplyDecisionFieldSet.java.
- Add support for filtering get decisions by entity type SESSION.
- Add support for $security_notification event.

1.4.1 (2017-10-25)
=================

- Fix typo in ApplyDecisionFieldSet.java.
- Throw a ServerException for all 5xx server responses.

1.4 (2017-02-28)
=================

- Add support for $verification event.
- Add support for $app and $browser complex fields.

1.3.1 (2017-01-26)
==================

- Make base model classes public.
- Packaging with "jar" instead of "pom".

1.3 (2017-01-17)
==================

- Add support for decisions API.

1.2 (2016-12-14)
==================

- Initial public release.

This microservice application exposes endpoints to retrieve information such as all available currencies (no authentication required),
details about exchange rates for a specified currency (no authentication required), currency exchange (specifying 'from', 'to', 'amount') for admin users,
and currency exchange with confirmation for any user.

It utilizes security measures to verify users according to the aforementioned service breakdown.
Additionally, email notifications are sent to the email address associated with the requesting user, triggered by an event.